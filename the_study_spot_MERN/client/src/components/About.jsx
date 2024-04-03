//D:\studyspot\client\src\components\About.jsx
import React from "react";
import "./styles.css";

function About() {
  return (
    <div>
      <header>
        <h1 class="page-title">About Us</h1>
      </header>
      <main>
        <section id="mission">
          <h2 class="section-title"> Mission</h2>
          <p class="section-description">
            Welcome to the website! I am a computer science student who is
            passionate about sharing my knowledge and experience with fellow
            students. As student myself, I understand the challenges that come
            with studying computer science and navigating the tech industry.
            That's why I created this website - to provide useful articles and
            resources that can help other students succeed in their studies and
            careers. My goal is to offer proper guidance and support to help you
            reach your full potential. We hope you find my website helpful and
            informative, and I look forward to hearing from you!.
          </p>
        </section>
        <section id="team">
          <h2 class="section-title">About me</h2>
          <ul class="team-members">
            <li class="team-member">
              <img src="profile.jpg" alt="profile_pic" class="member-photo" />
              <h3 class="member-name">Javeed Mohammad</h3>
            </li>
          </ul>
        </section>
        <section id="contact">
          <h3 class="contact">Contact Us</h3>
          <p class="section-description">
            123 Main Street
            <br />
            Anytown, USA 12345
            <br />
            Phone:
            <span class="phone-number">555-555-5555</span>
            <br />
            Email:
            <a href="mailto:info@company.com" class="email-address">
              info@company.com
            </a>
          </p>
        </section>
      </main>
      <footer>
        <p class="copy-right">&copy; 2023 Company Name</p>
      </footer>
    </div>
  );
}

export default About;
