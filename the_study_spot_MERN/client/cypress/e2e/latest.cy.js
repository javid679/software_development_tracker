describe("Latest Articles Page", () => {
  beforeEach(() => {
    // visit the Latest Articles page before each test
    cy.visit("http://localhost:3000/latest");
  });

  it("displays user's articles", () => {
    // mock the API response with sample data
    cy.intercept("GET", "http://localhost:8080/api/article/email/*", {
      fixture: "articles.json",
    });

    // check if articles are displayed on the page
    cy.get(".grid-item").should("have.length", 3);

    // check if the article details are displayed correctly
    cy.get(".grid-item:first-child h2").should("have.text", "Article 1");
    cy.get(".grid-item:first-child p:nth-of-type(1)").should("contain", "John Doe");
    cy.get(".grid-item:first-child p:nth-of-type(2)").should("contain", "Technology");
    cy.get(".grid-item:first-child p:nth-of-type(3)").should("contain", "Lorem ipsum dolor sit amet");

    // check if Edit and Delete buttons are present
    cy.get(".grid-item:first-child .actions a").should("have.text", "Edit");
    cy.get(".grid-item:first-child .actions button").should("have.text", "Delete");
  });

  it("deletes an article", () => {
    // mock the API response with sample data
    cy.intercept("GET", "http://localhost:8080/api/article/email/*", {
      fixture: "articles.json",
    });

    // mock the API response for the delete request
    cy.intercept("DELETE", "http://localhost:8080/api/article/delete/*", {
      statusCode: 200,
      body: {},
    });

    // click the Delete button of the first article
    cy.get(".grid-item:first-child .actions button").click();

    // check if the article is removed from the page
    cy.get(".grid-item").should("have.length", 2);
    cy.get(".grid-item:first-child h2").should("have.text", "Article 2");
  });

  it("navigates to the Edit Article page", () => {
    // mock the API response with sample data
    cy.intercept("GET", "http://localhost:8080/api/article/email/*", {
      fixture: "articles.json",
    });

    // click the Edit button of the first article
    cy.get(".grid-item:first-child .actions a").click();

    // check if the Edit Article page is displayed
    cy.url().should("contain", "/edit/");
    cy.get("h1").should("have.text", "Edit Article");
  });
});
