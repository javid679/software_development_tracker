//D:\studyspot\client\src\components\Contact.jsx

import React from "react";
import "./styles.css";

function Contact() {
  return (
    <div className="aboutcontent">
      <h1>Contact Us</h1>
      <h2>Get in touch</h2>
      <form>
        <label>
          Name:
          <input type="text" name="name" />
        </label>
        <label>
          Email:
          <input type="email" name="email" />
        </label>
        <label>
          Message:
          <textarea name="message" />
        </label>
        <button type="submit">Send</button>
      </form>
      <h2>Our Location</h2>
      <p>123 Main Street</p>
      <p>Anytown, USA 12345</p>
      <h2>Contact Information</h2>
      <ul>
        <li>Phone: (123) 456-7890</li>
        <li>Email: info@mycompany.com</li>
      </ul>
      <h2>Connect with us</h2>
      <ul>
        <li>Facebook</li>
        <li>Twitter</li>
        <li>instagram</li>
      </ul>
    </div>
  );
}

export default Contact;
