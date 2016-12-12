$(document).ready(function () {

    $('#saveSubject').submit(function (e) {
        $.post('/university/subjectAdd', $(this).serialize(), function (response) {

            if (response.hasError == true){
                $('#error').html(response.status);

            } else {
                $('#success').html(response.status);
                $('#subjectsTableResponse').last().append(
                    '<tr>' +
                    '<td align=\"center\">' + response.subject.title + '</td>' +
                    '<td align=\"center\">' + '<a href=\"subject/update/'+response.subject.id+'\">' + 'Редактировать' + '</a>'+'</td>'+
                    '<td align=\"center\">' + '<a href=\"subject/delete/'+response.subject.id+'\">' + 'Удалить' + '</a>'+'</td>'+
                    '</tr>'
                );
                clearInputs();

            }

        });
        e.preventDefault();
        clearMessages();
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

