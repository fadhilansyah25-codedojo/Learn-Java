function openForm() {
  $.ajax({
      type: "get",
      url: "/user/form",
      contentType: "html",
      success: function (UserForm) {
          $('#modalForm').modal('show');
          $('.modal-title').html('User Form');
          $('.modal-body').html(UserForm);
      }
  });
}

function editForm(id) {
  $.ajax({
      type: "get",
      url: `/user/edit/${id}`,
      contentType: "html",
      success: function (UserForm) {
          $('#modalForm').modal('show');
          $('.modal-title').html('User Form');
          $('.modal-body').html(UserForm);
      }
  });
}

function deleteUser (id) {
  // confirm("Are you sure?", 
  //     $.ajax({
  //         type: "delete",
  //         url: `/User/delete/${id}`,
  //         dataType: "html",
  //         success: function (response) {
  //             location.reload()
  //         }
  //     })
  // );
  $.ajax({
      type: "get",
      url: `/user/delete/${id}`,
      dataType: "html",
      success: function (response) {
          location.reload()
      }
  })
}