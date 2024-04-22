$(document).ready(function() {
    $('input[name="count"]').on('input', function() {
        var count = parseInt($(this).val());
        var price = parseInt($(this).closest('tr').find('.align-middle.text-center:eq(1)').text());
        var totalPrice = count * price;
        
        var prodNo = $(this).closest('tr').find('span').attr('id');
        $('#' + prodNo).text(totalPrice);
    });
});