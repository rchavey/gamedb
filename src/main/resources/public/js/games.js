/**
 * Created by Rachel on 9/11/2016.
 */

$(document).ready(function () {
    var types;
    var locations;

    $.getJSON("types", function (data) {
        types = data;

        $.getJSON("locations", function (stuff) {
            locations = stuff;
            $.getJSON("games", function (data) {
                renderTable(data);
            });
        });
    });

    $("#filters select, #filters input").change(function (e) {
        var numPlayers = $("#numPlayers").val();
        var type = $("#type").val();
        var location = $("#location").val();
        var length = $("#length").val();
        getFilteredGames(numPlayers, type, location, length);
    });

    $("body").on("click", "a.update-game", function(e) {

        var gameId = $(this).data('gameId');
        var lastPlayed = $(this).parents("tr").find('input[type=date]').val()
        $.ajax({
            type: 'put',
            url: 'asdf/' + gameId + '/last-played',
            data: JSON.stringify({lastPlayed: lastPlayed}),
            success: function (data) {
                console.log('saved successfully');
            },
            contentType: 'application/json',
            dataType: 'json'
        });
    });

    $("#saveGame").click(function (e) {
        var parameters = {
            name: $("#newName").val(),
            type: $("#newType").val(),
            minPlayers: $("#newMinPlayers").val(),
            maxPlayers: $("#newMaxPlayers").val(),
            length: $("#newLength").val(),
            locations: []
        };

        $("input[name=newLocation]:checked").each(function () {
            parameters.locations.push($(this).val());
        });

        addGame(parameters);
    });


    function getFilteredGames(numPlayers, type, location, length) {
        $.getJSON("filtered-games", {
            numPlayers: numPlayers,
            type: type,
            location: location,
            length: length
        }, function (data) {
            renderTable(data);
        });
    }

    function addGame(parameters) {
        $.ajax({
            type: 'post',
            url: 'addGame',
            data: JSON.stringify(parameters),
            success: function (data) {

                console.log('saved successfully');
            },
            contentType: 'application/json',
            dataType: 'json'

        });
    }

    function renderTable(data) {
        $("#games tbody").empty();
        for (var i = 0; i < data.length; i++) {
            var locationsString = "";
            for (var j = 0; j < data[i].locationIDs.length; j++) {
                locationsString += locations[data[i].locationIDs[j]]
                if (j != data[i].locationIDs.length - 1) {
                    locationsString += ", "
                }
            }

            // var lastPlayedInput = "<input type='date' value='" + data[i].lastPlayed + "'/>";
            var lastPlayedInput = data[i].lastPlayed || '';

            $("#games tbody").append("<tr> <td>" +
                data[i].name + "</td> <td>" +
                types[data[i].typeID] + "</td> <td>" +
                data[i].minPlayers + "</td> <td>" +
                data[i].maxPlayers + "</td> <td>" +
                data[i].lengthMinutes + "</td> <td>" +
                locationsString + "</td><td>" +
                lastPlayedInput + "</td></tr>");
                // "<td><a href='#' class='update-game' data-game-id='" + data[i].id + "'>Save</a></td></tr>");
        }

    }

});