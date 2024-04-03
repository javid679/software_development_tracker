describe("Signup component", () => {
  beforeEach(() => {
    cy.visit("http://localhost:3000/signup");
  });

  it("should display an error message when form is submitted with invalid data", () => {
    cy.get("input[name=firstName]").type("John");
    cy.get("input[name=lastName]").type("Doe");
    cy.get("input[name=email]").type("invalid-email"); // Invalid email format
    cy.get("input[name=password]").type("password123");
    cy.get("button[type=submit]").click();
  });

  it("should redirect to login page when form is submitted with valid data", () => {
    cy.get("input[name=firstName]").type("John");
    cy.get("input[name=lastName]").type("Doe");
    cy.get("input[name=email]").type("john.doe@example.com");
    cy.get("input[name=password]").type("password123");

    cy.get("button[type=submit]").click();
  });
});
