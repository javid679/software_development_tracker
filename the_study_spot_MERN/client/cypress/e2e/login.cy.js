describe("Login", () => {
  beforeEach(() => {
    cy.visit("http://localhost:3000/login"); // assuming the login page is at "/login" path
  });

  it("should display error message on invalid credentials", () => {
    cy.get("input[name=email]").type("invalid@example.com");
    cy.get("input[name=password]").type("invalid-password");
    cy.get("button[type=submit]").click();
  });

  it("should redirect to HomePage on successful login", () => {
    cy.get("input[name=email]").type("valid@example.com"); // replace with valid email
    cy.get("input[name=password]").type("valid-password"); // replace with valid password
    cy.get("button[type=submit]").click();
  });
});
