setInterval(loadHall, 30000);

$(document).ready(function () {
    loadHall();
});

function loadHall() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/job4j_cinema/index',
        dataType: "json"
    }).done(function (data) {
        let hall = "";
        let index = 0;
        for (let x = 0; x !== data.length; x++) {
            let cell = data[x];
            if (index === 0) {
                hall += "<tr><th>Ряд " + cell['row'] + "</th>";
            }
            index++;
            hall += validateAccountId(cell);
            if (index === 5) {
                hall += "</tr>";
                index = 0;
            }
        }
        $('#hall').html(hall);
    });
}

function validateAccountId(cell) {
    let hall;
    if (cell['accountId'] !== 0) {
        hall = "<td style='color: #ff0000'>" + "Место купленно" + "</td>";
    } else {
        hall = "<td style='color: #008000'><input type=\"checkbox\" name=\"place\"" +
            "value=" + cell['row'] + cell['cell'] + ">" + "Ряд " + cell['row'] + ", " + "Место " + cell['cell'] + "</td>";
    }
    return hall;
}

$(document).ready(function () {
    $('#choicePlace').on('click', function () {
        let values = [];
        $('[name="place"]:checked').each(function () {
            values.push($(this).val());
        });
        if (values.length !== 0) {
            let parameters = values.join(',');
            window.location.href = 'http://localhost:8080/job4j_cinema/payment.html?rowCell=' + parameters;
        } else {
            alert("Выберите место пожалуйста");
        }
    });
});