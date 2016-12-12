$(document).ready(function (){
    $('#saveStudent').submit(function (e) {
        $.post('/university/studentAdd', $(this).serialize(), function (response) {
            if (response.hasError == true){
                $('#error').html(response.status);
            } else {
                $('#success').html(response.status);
                $('#studentTableResponse').last().append(
                    '<tr>' +
                    '<td align=\"center\">' + response.student.firstName + '</td>' +
                    '<td align=\"center\">' + response.student.lastName + '</td>' +
                    '<td align=\"center\">' + response.student.entranceYear + '</td>' +
                    '<td align=\"center\">' + '<a href=\"student/profile/'+response.student.id+'\">' + 'Профиль' + '</a>' +'</td>'+
                    '</tr>'
                );
                clearInputs()
            }
        });
        e.preventDefault();
        clearMessages()
    });
});

function clearInputs() {
    $('input[id*="Input"]').each(function () {
        $(this).val('');
    });
}

function clearMessages() {
    $(".error").empty();
    $(".success").empty();
}