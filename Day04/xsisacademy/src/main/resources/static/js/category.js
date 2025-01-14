function openForm() {
  $.ajax({
      type: "get",
      url: "/category/form",
      contentType: "html",
      success: function (categoryForm) {
          $('#modalForm').modal('show');
          $('.modal-title').html('Category Form');
          $('.modal-body').html(categoryForm);
      }
  });
}

function editForm(id) {
  $.ajax({
      type: "get",
      url: `/category/edit/${id}`,
      contentType: "html",
      success: function (categoryForm) {
          $('#modalForm').modal('show');
          $('.modal-title').html('Category Form');
          $('.modal-body').html(categoryForm);
      }
  });
}

function deleteCategory (id) {
  // confirm("Are you sure?", 
  //     $.ajax({
  //         type: "delete",
  //         url: `/category/delete/${id}`,
  //         dataType: "html",
  //         success: function (response) {
  //             location.reload()
  //         }
  //     })
  // );
  $.ajax({
      type: "get",
      url: `/category/delete/${id}`,
      dataType: "html",
      success: function (response) {
          location.reload()
      }
  })
}