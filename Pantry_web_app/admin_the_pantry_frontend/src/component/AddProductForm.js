import React, { useState } from 'react'
import axios from 'axios';

function AddProductForm({ AdminLogin }) {
    const [details, setDetails] = useState({product: "", category: "", quantity: 0})

    const exportPDF = (e)=>{
        e.preventDefault();

        fetch('http://localhost:8080/api/export/pdf').then(response => {
            response.blob().then(blob => {
                const fileURL = window.URL.createObjectURL(blob);
                let alink = document.createElement('a');
                alink.href = fileURL;
                alink.download = 'Product Catalog.pdf';
                alink.click();
            })
    })
    }

    const exportOrderPDF = (e)=>{
        e.preventDefault();

        fetch('http://localhost:8080/api/export/orderpdf').then(response => {
            response.blob().then(blob => {
                const fileURL = window.URL.createObjectURL(blob);
                let alink = document.createElement('a');
                alink.href = fileURL;
                alink.download = 'Order Catalog.pdf';
                alink.click();
            })
    })
    }
  
    const submitHandler = e => {
        e.preventDefault();

        axios.post('http://localhost:8080/rest-api/admin/AddProduct', {
        product_name: details.product,
        cat_id: details.category,
        qty: details.quantity
        })
      .then(function (response) {
        if(response.status===200)
        {
            alert("Product added")
            setDetails(()=>({product: "", category: "", quantity: 0}))
        }
        else
        {
          alert("Something went Wrong. Please try Again!!")
        }
      })
      .catch(function (error) {
        console.log(error)
        alert("Invalid email or password")
      });
    }
  
    return (
        <form onSubmit={submitHandler}>
            <div className="form-inner">
                <div style={{display: "inline"}}>
                    <input style={{width: "30%", float: "right"}} type="button" value="Products" onClick={exportPDF} />
                    <input style={{width: "30%", float: "right"}} type="button" value="Orders" onClick={exportOrderPDF} />
                    <h2>Add Product</h2>
                </div>
                <div className="form-group">
                    <label id="productNameLabel" htmlFor="productName">Product name:</label>
                    <input type="text" name="productName" id="productName" onChange={e => {setDetails({...details, product: e.target.value})}} value={details.product}/>
                </div>
                <div className="form-group">
                    <label id="categoryLabel" htmlFor="category">Category Id:</label>
                    <input type="text" name="category" id="category" onChange={e => {setDetails({...details, category: e.target.value})}} value={details.category}/>
                </div>
                <div className="form-group">
                    <label id="quantityLabel" htmlFor="quantity">Quantity:</label>
                    <input type="number" name="quantity" id="quantity" onChange={e => {setDetails({...details, quantity: e.target.value})}} value={details.quantity}/>
                </div>
                <input type="submit" value="Add" />
                <input type="button" value="Log Out" onClick={AdminLogin} />
                </div>
        </form>
    )
}

export default AddProductForm