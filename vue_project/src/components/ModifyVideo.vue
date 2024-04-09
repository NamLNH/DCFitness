<template>
  <!--Header for web-->
  <WebHeader/>

  <!--end of header-->
  <div class="add-title" style="margin-left: 20px; margin-right: 20px">
    <h2>Modify video</h2>
  </div>
  <div class="card-body" style="margin-left: 20px; margin-right: 20px">
    <form method="" action="">
      <div class="row">
        <div class="col">
          <div class="mb-3 row">
            <label
              class="col-lg-2 col-md-6 col-sm-12 col-form-label title-label"
              >Title:</label
            >
            <div class="col-lg-10 col-md-6 col-sm-12 title-input">
              <input
                name="name"
                value=""
                type="text"
                class="form-control"
                required
              />
            </div>
          </div>
        </div>
        <div class="col">
          <div class="mb-3 row">
            <label class="col-lg-2 col-md-6 col-sm-12 col-form-label"
              >Url:
            </label>
            <div class="col-lg-10 col-md-6 col-sm-12">
              <input
                name="isbn"
                value=""
                type="text"
                class="form-control"
                required
              />
            </div>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col">
          <div class="mb-3 row">
            <label class="col-lg-2 col-md-6 col-sm-12 col-form-label"
              >Category:
            </label>
            <div class="col-lg-10 col-md-6 col-sm-12">
              <select name="category" class="form-control" required>
                <option value="1">Option 1</option>
                <option value="2">Option 2</option>
                <option value="3">Option 3</option>
              </select>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="mb-3 row">
            <label class="col-lg-2 col-md-6 col-sm-12 col-form-label"
              >Author:
            </label>
            <div class="col-lg-10 col-md-6 col-sm-12">
              <input
                name="quantity"
                value=""
                type="text"
                class="form-control"
                required
              />
            </div>
          </div>
        </div>
      </div>
      <div class="submit-div">
        <div class="btn update-div">
          <button
            type="submit"
            class="btn btn-primary btn-update"
            name="action"
            value="update"
          >
            Update Book
          </button>
        </div>
        <div class="btn delete-div">
          <button
            type="submit"
            class="btn btn-danger btn-delete"
            name="action"
            value="delete"
          >
            Delete Book
          </button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import WebHeader from "@/components/WebHeader.vue"

export default {
  data() {

  },
  components :{
    WebHeader
  }
};
</script>

<style>
</style>