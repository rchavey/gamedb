/**
 * Created by Rachel on 9/11/2016.
 */

$(document).ready(function() {
    $.getJSON("games", function(data) {
        $("#games").append(JSON.stringify(data));
    });

    //format data
    $("#filters select, #filters input").change(function(e) {
        var numPlayers = $("#numPlayers").val();
        var type = $("#type").val();
        var location = $("#location").val();
        var length = $("#length").val();
        getFilteredGames(numPlayers, type, location, length);
    });

    $("#saveGame").click(function (e)  {
        var parameters = {name: $("#newName").val(),
            type:$("#newType").val(),
            minPlayers:$("#newMinPlayers").val(),
            maxPlayers:$("#newMaxPlayers").val(),
            length:$("#newLength").val(),
            locations: []
        };

        $("input[name=newLocation]:checked").each(function () {
            parameters.locations.push($(this).val());
        });

        addGame(parameters);
    });


    function getFilteredGames(numPlayers, type, location, length) {
        $.getJSON("filtered-games",{numPlayers: numPlayers, type: type, location: location, length: length}, function(data) {
            $("#games").html(JSON.stringify(data));
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

});