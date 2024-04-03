describe("HomePage", () => {
  beforeEach(() => {
    cy.visit("http://localhost:3000/HomePage");
  });

  it("displays the home page with correct elements", () => {
    cy.get(".username").should("contain", "Hello");
    cy.get(".title").should("contain", "Welcome to our website!");
    cy.get(".description").should(
      "contain",
      "Join our platform to discover and share insightful articles"
    );
    cy.get(".all-articles-button").should("contain", "Explore Articles");
    cy.get(".navbar").should("exist");
    cy.get(".navbuton").should("have.length", 4);
  });

  it("navigates to the display page when the 'Explore Articles' button is clicked", () => {
    cy.get(".all-articles-button").click();
    cy.url().should("include", "/display");
  });

  it("logs out when the 'Logout' button is clicked", () => {
    cy.get(".navbar").contains("Logout").click();
    cy.url().should("include", "/login");
  });
});
