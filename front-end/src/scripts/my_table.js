(function($) {
  let url = "http://localhost:8081/api/person";
  $.get(url, function(data, status) {
    console.log("Data: " + data + "\nStatus: " + status);
    $('#myTable').DataTable({
      "destroy": true, // In order to reinitialize the datatable
      "pagination": true, // For Pagination
      "sorting": true, // For sorting
      "aaData": data,
      "columns": [{
        "data": "id"
      }, {
        "data": "firstName"
      }, {
        "data": "lastName"
      }]
    });
  });


})(jQuery);
