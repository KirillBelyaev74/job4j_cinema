$(document).ready(function () {
    let map = getRowAndCell();
    let choiceClient = "";
    map.forEach((value, key) => {
        choiceClient += "<p><h3>Вы выбрали ряд " + key + ", место " + value + "</h3></p>";
    });
    choiceClient += "<p>Общая сумма состовляет " + (map.size * 500) + " рублей</p><br>";
    $('#h3').html(choiceClient);
});

$(document).ready(function () {
    $('#button').on('click', function () {
        let name = $('#name').val();
        let email = $('#email').val();
        let phone = $('#phone').val();
        if (email === '' || phone === '' || name === '') {
            alert("Заполните все поля");
        } else {
            let map = getRowAndCell();
            let rows = [];
            let cells = [];
            map.forEach((value, key) => {
                rows.push(key);
                cells.push(value);
            });
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/job4j_cinema/payment',
                dataType: 'json',
                data: {"name": name, "email": email, "phone": phone, "rows": rows.join(","), "cells": cells.join(",")}
            }).done(function (data) {
                let stingJson = JSON.stringify(data);
                let purchasedPlace = JSON.parse(stingJson);
                let message = "";
                for (let index = 0; index !== purchasedPlace.rows.length; index++) {
                    message += "Вы купили билет: ряд " + purchasedPlace.rows[index] + " место " + purchasedPlace.cells[index] + "\n";
                }
                alert(message);
                window.location.href = 'http://localhost:8080/job4j_cinema/';
            }).fail(function(err){
                alert("Оплата не прошла!" + err);
            });
        }
    });
});

function getRowAndCell() {
    let params = new URLSearchParams(window.location.search);
    let arr = params.get('rowCell').split(',');
    let map = new Map();
    for (let i = 0; i !== arr.length; i++) {
        map.set(arr[i].substring(0, 1), arr[i].substring(1));
    }
    return map;
}