<template>
    <div class="add-title">
        <h2>Manage Book</h2>
    </div>
    <div class="card-body">

        <form method="" action="">
            
            <div class="row">
            <div class="col">
                <div class="mb-3 row">
                    <label class="col-lg-2 col-md-6 col-sm-12 col-form-label title-label">Title:</label>
                    <div class="col-lg-10 col-md-6 col-sm-12 title-input">
                        <input name="name" value="" type="text" class="form-control" required>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="mb-3 row">
                    <label class="col-lg-2 col-md-6 col-sm-12 col-form-label">ISBN: </label>
                    <div class="col-lg-10 col-md-6 col-sm-12">
                        <input name="isbn" value="" type="text" class="form-control" required>
                    </div>
                </div>
            </div>
            </div>
    
            <div class="row">
                <div class="col">
                    <div class="mb-3 row">
                        <label class="col-lg-2 col-md-6 col-sm-12 col-form-label">Category: </label>
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
                        <label class="col-lg-2 col-md-6 col-sm-12 col-form-label">Quantity: </label>
                        <div class="col-lg-10 col-md-6 col-sm-12">
                            <input name="quantity" value="" type="text" class="form-control" required>
                        </div>
                    </div>
                </div>
            </div>
            <div class="mb-3">
                <label class="form-label">Description</label>
                <textarea class="form-control" name="description" rows="3" required>Description</textarea>
            </div>
            <input type="hidden" name="id" value="">
            <div class="submit-div">
                <div class="btn update-div"><button type="submit" class="btn btn-primary btn-update" name="action" value="update">Update Book</button></div>
                <div class="btn delete-div"><button type="submit" class="btn btn-danger btn-delete" name="action" value="delete">Delete Book</button></div>
            </div>
        </form>

    </div>
    
</template>

<script>
export default {
    setup() {
        
    },
}
</script>

<style>

</style>