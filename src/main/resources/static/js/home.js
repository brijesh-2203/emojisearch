$(document).ready(function ()
{

   $("input").on('keyup',function (e)
   {
       var val = $("#search-id").val();
       console.log(val)
       e.preventDefault()
       $.ajax({
           type: "GET",
           url: "/emoji/"+val,
           success: function(data) {
               console.log(data)
               $("#emojis").html(data)
           },
           error: function(textStatus) {
               $("#emojis").html('');
             console.log("error")
           },
           processData: false,
           contentType: false
       });
   })

})