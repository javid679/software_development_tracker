//D:\studyspot\client\src\components\editArticle.jsx
import React, { useState, useEffect } from "react";
import axios from "axios";

function EditArticle({ articleId }) {
  const [article, setArticle] = useState({});

  useEffect(() => {
    axios
      .get(`/api/article/articleid/${articleId}`)
      .then((response) => {
        setArticle(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [articleId]);

  const handleSubmit = (event) => {
    event.preventDefault();
    axios
      .put(`/api/article/${articleId}`, article)
      .then((response) => {
        console.log(response.data);
        // TODO: show a success message to the user
      })
      .catch((error) => {
        console.log(error);
        // TODO: show an error message to the user
      });
  };

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setArticle((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  return (
    <div>
      <h1>Edit Article</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="title">Title:</label>
          <input
            type="text"
            id="title"
            name="title"
            value={article.title || ""}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label htmlFor="name">Name:</label>
          <input
            type="text"
            id="name"
            name="name"
            value={article.name || ""}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label htmlFor="category">Category:</label>
          <input
            type="text"
            id="category"
            name="category"
            value={article.category || ""}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label htmlFor="description">Description:</label>
          <textarea
            id="description"
            name="description"
            value={article.description || ""}
            onChange={handleInputChange}
          />
        </div>
        <button type="submit">Save Changes</button>
      </form>
    </div>
  );
}

export default EditArticle;
