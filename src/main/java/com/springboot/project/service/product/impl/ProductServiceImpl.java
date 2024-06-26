package com.springboot.project.service.product.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mvc.common.util.CommonUtil;
import com.mvc.common.util.WeatherCode;
import com.mvc.common.util.WeatherUtill;
import com.springboot.project.service.domain.product.CartVO;
import com.springboot.project.service.domain.product.FileVO;
import com.springboot.project.service.domain.product.ProductTagVO;
import com.springboot.project.service.domain.product.ProductVO;
import com.springboot.project.service.domain.product.SearchVO;
import com.springboot.project.service.domain.product.TagDataVO;
import com.springboot.project.service.domain.product.TagVO;
import com.springboot.project.service.domain.statistics.ProductCountByTagVO;
import com.springboot.project.service.domain.statistics.ProductCountByTransactionVO;
import com.springboot.project.service.domain.user.UserVO;
import com.springboot.project.service.product.ProductDAO;
import com.springboot.project.service.product.ProductService;
import com.springboot.project.service.purchase.PurchaseDAO;


@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	@Qualifier("weatherUtil")
	private WeatherUtill weatherUtil;
	
	@Autowired
	@Qualifier("productDAOImpl")
	private ProductDAO productDAO;
	
	@Autowired
	@Qualifier("purchaseDAOImpl")
	private PurchaseDAO purchaseDAO;
	
	private String path = "C://Project_Eclipse/MiniProject/src/main/resources/static/img/uploadFiles/";
	
	public ProductServiceImpl() {
		System.out.println("[" + getClass().getName() + " Default Constructor] Call");
	}
	
	public ProductServiceImpl(ProductDAO productDAO) {
		System.out.println("[" + getClass().getName() + " ProductDAO set Constructor] Call");
		this.productDAO = productDAO;
	}
	
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	@Override
	public ProductVO getProduct(int productId) {
		ProductVO product = null;
		List<FileVO> fileList = null;
		List<String> fileName = new ArrayList<String>();
		
		try {
			product = productDAO.getProduct(productId);
			fileList = productDAO.getProductImage(productId);
			if(fileList != null && fileList.size() > 0) {
				fileList.stream().forEach(s -> {
					fileName.add(s.getFileName());
				});
				
				product.setFileName(fileName);
			}
		} catch (Exception e) {
			System.out.println(getClass().getName() + ".findProduct Exception");
			e.printStackTrace();
		}
		
		return product;
	}
	
	@Override
	public Map<String, Object> getProductList(SearchVO searchVO) {	
		Map<String, Object> map = new HashMap<String, Object>();
		Map<Integer, String> fileNameMap  = new HashMap<Integer, String>();
		List<ProductVO> list = null;
		List<FileVO> fileList = null;
		List<String> fileName = new ArrayList<String>();
		
		int totalCount = 0;
		
		// 번호나 정수에 대해서 한글 입력에 대한 유효성 처리
		if(searchVO != null) {
			String searchCondition = searchVO.getSearchCondition();
			String serachKeyword = searchVO.getSearchKeyword();
			
			if(searchCondition != null && serachKeyword != null) {
				if (searchCondition.equals("prodNo") || searchCondition.equals("price")) {
					serachKeyword = CommonUtil.notNumToNum(serachKeyword);
				} else if (searchCondition.equals("prodName")) {
					serachKeyword = CommonUtil.null2str(serachKeyword);
				}
				System.out.println("searchKeyword : " + serachKeyword);
				
				searchVO.setSearchKeyword(serachKeyword);
			}
		}
		
		// productImage 관련 데이터를 가져옴
		try {
			list = productDAO.getProductList(searchVO);
			totalCount = productDAO.getProductCount(searchVO);
			for(ProductVO p : list) {
				// fileName을 가져와서 productVO에 따로 저장
				fileList = productDAO.getProductImage(p.getProdNo());
				if(fileList != null) {
					fileName.add(fileList.get(0).getFileName());
					p.setFileName(fileName);
					fileName = new ArrayList<String>();
				}
			}
		} catch (Exception e) {
			System.out.println(getClass().getName() + ".getProduct Exception");
			e.printStackTrace();
		}
		map.put("list", list);
		map.put("totalCount", totalCount);
		map.put("fileNameMap", fileNameMap);
		
		return map;
	}

	@Transactional
	@Override
	public int addProduct(ProductVO productVO, List<MultipartFile> multipartFiles, String tagList) {
		int result = 0;
		
		// 1. 제품 정보 추가
		result = productDAO.addProduct(productVO);
		
		// 2. 제품 이미지 추가
		// 0) 데이터 검증
		boolean test = true;
		for(MultipartFile f : multipartFiles) {
			String originalFileName = f.getOriginalFilename();
			if(originalFileName.equals("") || originalFileName == null) {
				test = false;
				break;
			}
		}
		
		if(multipartFiles != null && multipartFiles.size() > 0) {
			for(MultipartFile f : multipartFiles) {
				String originalFileName = f.getOriginalFilename();
				UUID uuid  = UUID.randomUUID();			// 유일자 식별은 java.util.UUID를 이용한다.
				String fileName = uuid + originalFileName;
				
				try {
					f.transferTo(new File(path + fileName));
				} catch (IllegalStateException | IOException e) {
					System.out.println(getClass().getName() + ".addProductImage Exception");
					e.printStackTrace();
				}
	
				FileVO file = new FileVO();
				file.setFileName(fileName);
				productDAO.addProductImage(file);
			}
		}
		
		// 3. 태그 정보 추가
		String[] tags = tagList.split(",");
		for(String tagName : tags) {
			TagVO tag = productDAO.getTag(tagName); result++;

			// 새로운 태그라면 tag 테이블에 추가
			if(tag == null) {
				productDAO.addTag(tagName); result++;
				tag = productDAO.getTag(tagName); result++;
			}
			
			// product_tag 테이블에 추가
			productDAO.addProductTagWithSeq(tag.getTagNo()); result++;
		}
		
		return result;
	}

	@Transactional
	@Override
	public int updateProduct(ProductVO productVO, List<MultipartFile> multipartFiles) {
		int result = 0;
		
		// 1. 제품 정보 업데이트
		result += productDAO.updateProduct(productVO);
		
		// 2. 제품 이미지 수정 : 기존에 있는 정보 삭제 후 다시 추가하기
		// 0) 데이터 검증
		boolean test = true;
		for(MultipartFile f : multipartFiles) {
			String originalFileName = f.getOriginalFilename();
			if(originalFileName.equals("") || originalFileName == null) {
				test = false;
				break;
			}
		}
		
		if(multipartFiles != null && multipartFiles.size() > 0 && test) {
			// 1) 삭제
			result += productDAO.deleteProductImage(productVO.getProdNo());
			
			// 2) 다시 이미지 추가
			for(MultipartFile f : multipartFiles) {
				String originalFileName = f.getOriginalFilename();
				UUID uuid  = UUID.randomUUID();			// 유일자 식별은 java.util.UUID를 이용한다.
				String fileName = uuid + originalFileName;
				
				try {
					f.transferTo(new File(path + fileName));
				} catch (IllegalStateException | IOException e) {
					System.out.println(getClass().getName() + ".addProductImage Exception");
					e.printStackTrace();
				}		
				
				FileVO file = new FileVO();
				file.setFileName(fileName);
				file.setProdNo(productVO.getProdNo());
				productDAO.updateAddProductImage(file);
			} 
		}
		
		return result;
	}

	@Transactional
	@Override
	public int deleteProduct(ProductVO product) {
		int result = 0;
		
		result += productDAO.deleteProduct(product.getProdNo());
		
		return result;
	}


	@Transactional
	@Override
	public int addProductImage(FileVO file) {
		int result = 0;
		
		result = productDAO.addProductImage(file);
		
//		try {
//			result = productDAO.addProductImage(file);
//		} catch (Exception e) {
//			System.out.println(getClass().getName() + ".addProductImage Exception");
//			e.printStackTrace();
//		}
		
		return result;
	}

	@Override
	public List<FileVO> getProductImage(int prodNo) {
		List<FileVO> result = null;
		
		try {
			result = productDAO.getProductImage(prodNo);
		} catch (Exception e) {
			System.out.println(getClass().getName() + ".updateProductImage Exception");
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<String> getProductInfo(String key) {
		List<ProductVO> queryResult = null;
		List<String> result = new ArrayList<String>();
		
		try {
			queryResult = productDAO.getProductInfo();
			if(queryResult != null && queryResult.size() >= 0) {
				queryResult.stream().forEach(s -> {
					if(key.equals("prodName")) {
						result.add(s.getProdName());
					} else if (key.equals("prodNo")) {
						result.add(String.valueOf(s.getProdNo()));
					}
				});
			}
			
		} catch (Exception e) {
			System.out.println("[" + getClass().getName() + ".getUserIdAndUserNames] Exception");
			e.printStackTrace();
		}
		
		return result.stream().distinct().collect(Collectors.toList());
	}

	@Transactional
	@Override
	public int addCart(CartVO cart) {
		return productDAO.addCart(cart);
	}

	@Transactional
	@Override
	public int deleteCart(CartVO cart) {
		return productDAO.deleteCart(cart);
	}

	@Override
	public boolean checkIsCart(CartVO cart) {
		int result = productDAO.checkIsCart(cart);
		
		if(result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Map<String, Object> getCartList(String userId) {
		List<CartVO> cartList = productDAO.getCartList(userId);
		List<ProductVO> productList = new ArrayList<ProductVO>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		cartList.stream().forEach(s -> {
			productList.add(productDAO.getProduct(s.getProdNo()));
		});
		
		resultMap.put("cartList", cartList);
		resultMap.put("productList", productList);
		
		return resultMap;
	}
	
	@Transactional
	@Override
	public int addTag(TagDataVO tagData) {
		int result = 0;
		String tagName = tagData.getTagName();
		
		TagVO tag = productDAO.getTag(tagName); result++;

		// 새로운 태그라면 tag 테이블에 추가
		if(tag == null) {
			productDAO.addTag(tagName); result++;
			tag = productDAO.getTag(tagName); result++;
		}
		
		// product_tag 테이블에 추가
		ProductTagVO productTag = new ProductTagVO(tagData.getProdNo(), tag.getTagNo()); 
		productDAO.addProductTag(productTag); result++;
		
		return result;
	}

	@Transactional
	@Override
	public int deleteProductTag(TagDataVO tagData) {
		int result = 0;
		
		productDAO.deleteProductTag(tagData.getTagName()); result++;
		
		return result;
	}

	@Override
	public List<TagVO> getTagFromProduct(int prodNo) {
		
		List<TagVO> resultList = productDAO.getTagFromProduct(prodNo);
		
		return resultList;
	}

	@Override
	public Map<String, Object> getWeatherRecommendProduct(int size) {
		WeatherCode todayWeather = WeatherCode.DEFAULT;
		
		try {
			todayWeather = weatherUtil.getTodayWeather();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Error Defending
		if(todayWeather.equals(WeatherCode.DEFAULT)) {
			return null;
		}
		
		List<ProductVO> resultList = productDAO.getWeatherRecommendProduct(todayWeather.getTagNo());
		
		Collections.shuffle(resultList);
		resultList = resultList.subList(0, size);
		System.out.println(resultList);
		
		// productImage 관련 데이터를 가져옴
		List<String> fileName = new ArrayList<String>();
		try {
			for(ProductVO p : resultList) {
				// fileName을 가져와서 productVO에 따로 저장
				List<FileVO> fileList = productDAO.getProductImage(p.getProdNo());
				if(fileList != null) {
					fileName.add(fileList.get(0).getFileName());
					p.setFileName(fileName);
					fileName = new ArrayList<String>();
				}
			}
		} catch (Exception e) {
			System.out.println(getClass().getName() + ".getProduct Exception");
			e.printStackTrace();
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("weather", todayWeather.getContent());
		resultMap.put("resultList", resultList);
		
		return resultMap;
	}

	@Override
	public List<ProductVO> getCartRecommendProduct(int prodNo, int size) {
		
		List<Integer> tagList = productDAO.getTagListByProdNo(prodNo);
		
		// 태그가 없으면 null 반환하기
		if(tagList == null || tagList.size() <= 0) {
			return null;
		}
		
		List<ProductVO> resultList = null;
		while(resultList == null || resultList.size() == 0) {
			Collections.shuffle(tagList);
			resultList = productDAO.getProductListByTagNo(tagList.get(0));
			
			// 같은 제품은 걸러내기
			ListIterator<ProductVO> iterator = resultList.listIterator();
			while(iterator.hasNext()) {
				ProductVO p = iterator.next();
				if(p.getProdNo() == prodNo) {
					System.out.println(p);
					iterator.remove();
				}
			}
		}

		

		
		Collections.shuffle(resultList);
		if(resultList.size() > size) {
			resultList = resultList.subList(0, size);
		}
		
		// productImage 관련 데이터를 가져옴
		List<String> fileName = new ArrayList<String>();
		try {
			for(ProductVO p : resultList) {
				// fileName을 가져와서 productVO에 따로 저장
				List<FileVO> fileList = productDAO.getProductImage(p.getProdNo());
				if(fileList != null) {
					fileName.add(fileList.get(0).getFileName());
					p.setFileName(fileName);
					fileName = new ArrayList<String>();
				}
			}
		} catch (Exception e) {
			System.out.println(getClass().getName() + ".getProduct Exception");
			e.printStackTrace();
		}
		
		return resultList;
	}

	@Override
	public List<ProductCountByTagVO> getProductCountByTagName() {
		return productDAO.getProductCountByTagName();
	}

	@Override
	public List<ProductCountByTransactionVO> getProductCountByTransaction() {
		return productDAO.getProductCountByTransaction();
	}
}
