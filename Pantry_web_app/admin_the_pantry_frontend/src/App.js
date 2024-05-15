import React, { useState } from 'react'
import AddProductForm from './component/AddProductForm';
import AdminLoginForm from './component/AdminLoginForm';

function App() {
  const [page, setPage] = useState("AdminLogin");

  const AdminLogin = () =>{
    setPage("AdminLogin")
  }

  const AddProduct = () =>{
    setPage("AddProduct")
  }

  const Page = () =>{
    switch (page){
      case "AdminLogin":
        return <AdminLoginForm AddProduct={AddProduct} />
      case "AddProduct":
        return <AddProductForm AdminLogin={AdminLogin} />
      default:
        console.log("Error")
    }
  }

  return (
    <div className="App">
      {Page()}
    </div>
  )
}

export default App