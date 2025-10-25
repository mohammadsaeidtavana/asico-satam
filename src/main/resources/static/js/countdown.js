// Javascript
$(function() {
  var currentDate = new Date();
  $('.countdown').countdown(25 * 24 * 60 * 60 * 1000 + currentDate.valueOf(), function(event) {
    $this = $(this);
    switch(event.type) {
      case "seconds":
      case "minutes":
      case "hours":
      case "days":
      case "weeks":
      case "daysLeft":
        $this.find('div.'+event.type).html(event.value);
        break;
      case "finished":
        $this.fadeTo('slow', .5);
        break;
    }
  });
});


/* share URL: http://www.20script.ir */