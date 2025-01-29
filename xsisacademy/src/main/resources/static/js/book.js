function openForm() {
    $.ajax({
        type: "get",
        url: "/book/form",
        contentType: "html",
        success: function (auhtorForm) {
            $('#modalForm').modal('show');
            $('.modal-title').html('Book Form');
            $('.modal-body').html(auhtorForm);
        }
    });
  }
  
  function editForm(id) {
    $.ajax({
        type: "get",
        url: `/book/edit/${id}`,
        contentType: "html",
        success: function (UserForm) {
            $('#modalForm').modal('show');
            $('.modal-title').html('User Form');
            $('.modal-body').html(UserForm);
        }
    });
  }
  
  function deleteBook (id) {
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
        url: `/book/delete/${id}`,
        dataType: "html",
        success: function (response) {
            location.reload()
        }
    })
  }