function openForm() {
    $.ajax({
        type: "get",
        url: "/publisher/form",
        contentType: "html",
        success: function (publisherForm) {
            $('#modalForm').modal('show');
            $('.modal-title').html('Publisher Form');
            $('.modal-body').html(publisherForm);
        }
    });
  }
  
  function editForm(id) {
    $.ajax({
        type: "get",
        url: `/publisher/edit/${id}`,
        contentType: "html",
        success: function (publisherForm) {
            $('#modalForm').modal('show');
            $('.modal-title').html('User Form');
            $('.modal-body').html(publisherForm);
        }
    });
  }
  
  function deletePublisher (id) {
    // confirm("Are you sure?", 
    //     $.ajax({
    //         type: "delete",
    //         url: `/user/delete/${id}`,
    //         dataType: "html",
    //         success: function (response) {
    //             location.reload()
    //         }
    //     })
    // );
    $.ajax({
        type: "get",
        url: `/publisher/delete/${id}`,
        dataType: "html",
        success: function (response) {
            location.reload()
        }
    })
  }