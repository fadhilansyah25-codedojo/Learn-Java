function openForm() {
    $.ajax({
        type: "get",
        url: "/author/form",
        contentType: "html",
        success: function (auhtorForm) {
            $('#modalForm').modal('show');
            $('.modal-title').html('Author Form');
            $('.modal-body').html(auhtorForm);
        }
    });
  }
  
  function editForm(id) {
    $.ajax({
        type: "get",
        url: `/author/edit/${id}`,
        contentType: "html",
        success: function (UserForm) {
            $('#modalForm').modal('show');
            $('.modal-title').html('User Form');
            $('.modal-body').html(UserForm);
        }
    });
  }
  
  function deleteAuthor (id) {
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
        url: `/author/delete/${id}`,
        dataType: "html",
        success: function (response) {
            location.reload()
        }
    })
  }