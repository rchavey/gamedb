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

    $("saveGame").click(function e) {
        var name = $("#newName").val()
        var type = $("#newType").val()
        var minPlayers = $("#newMinPlayers").val()
        var maxPlayers = $("#newMaxPlayers").val()
        var length = $("#newLength").val()
        //be able to take in multiple locations
        var location = $("#newLocation").val()

        addGame(name, type, minPlayers,maxPlayers, length, location)
    }


    function getFilteredGames(numPlayers, type, location, length) {
        $.getJSON("filtered-games",{numPlayers: numPlayers, type: type, location: location, length: length}, function(data) {
            $("#games").html(JSON.stringify(data));
        });
    }

});