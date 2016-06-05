/**
 * Created by aleksandr on 13.02.2016.
 */
$(function(){
    $(".btn.collapse-document").click(function() {
        var collapseElementId = $(this).data('target');
        if($(collapseElementId).hasClass("collapse")) {
            $(collapseElementId).show('slow');
            $(collapseElementId).removeClass("collapse");
        } else {
            $(collapseElementId).hide('slow');
            $(collapseElementId).addClass("collapse");
        }
    });


// Load readme content
    $.ajax({
        url: "https://raw.githubusercontent.com/fedotovaleksandr/ProjectEnergo/master/README.md",
        dataType: 'text',
        success: function(data) {

            // Convert readme from markdown to html
            var converter = new Markdown.Converter();

            // Show html
            $(".wrapper").html(converter.makeHtml(data));

        }
    });
})
