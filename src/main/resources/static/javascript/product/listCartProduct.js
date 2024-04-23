$(document).ready(function() {
    $('input[name="count"]').on('input', function() {
        let count = parseInt($(this).val());
        let price = parseInt($(this).closest('tr').find('.align-middle.text-center:eq(1)').text());
        let totalPrice = count * price;
               
        if(isNaN(totalPrice)) {
			totalPrice = 0;
		}
		
        let prodNo = $(this).closest('tr').find('span').attr('id');
        $('#' + prodNo).text(totalPrice);
        
        let totalTransactionPrice = 0;
        $("input[name='count']").each(function() {
			    let eachCount = parseInt($(this).val());
        		let eachPrice = parseInt($(this).closest('tr').find('.align-middle.text-center:eq(1)').text());
        		let eachTotalPrice = count * price;
        		
        		totalTransactionPrice += eachTotalPrice;
		});
		
		console.log(totalTransactionPrice);
		
		if(isNaN(totalTransactionPrice)) {
			totalTransactionPrice = 0;
		}
		
		$("input[name='totalPrice']").val(totalTransactionPrice);
    });
});
