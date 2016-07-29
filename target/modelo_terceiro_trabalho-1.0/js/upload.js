/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function fazerUpload(){
console.log("cheguei na função");
var data = new FormData($('#idForm')[0]);
$.ajax(
        {
            type: 'POST',
            url: 'rest/fazerUpload',
            data: data,
            dataType: 'text',
            cache: false,
            async: true,
            processData: false,
            contentType: false, 
            success: function () {
                $('#idMensagem').html('Subiu o arquivo!');
            },
            error: function (xhr, ajaxOptions, thrownError) {
                $('#idMensagem').html(
                        'Erro AJAX:<br>' + xhr +
                        ' , ' + ajaxOptions +
                        ' , ' + thrownError);
            }
        }
);
console.log("saí na função");
}
