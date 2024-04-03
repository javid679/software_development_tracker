describe('About component', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000/About');
  });

  it('should display the page title', () => {
    cy.get('.page-title').should('contain', 'About Us');
  });

  it('should display the mission section', () => {
    cy.get('#mission').should('exist');
  });

  it('should display the team section', () => {
    cy.get('#team').should('exist');
  });

  it('should display the contact section', () => {
    cy.get('#contact').should('exist');
  });

  it('should display the team members', () => {
    cy.get('.team-member').should('have.length', 2);
  });

  it('should display the team member names', () => {
    cy.get('.member-name').should('contain', 'Surya Teja Varma Mudunuru');
    cy.get('.member-name').should('contain', 'Javeed Mohammad');
  });

  it('should display the company name in the footer', () => {
    cy.get('.copy-right').should('contain', 'Company Name');
  });

  it('should display the correct contact information', () => {
    cy.get('.section-description')
      .contains('123 Main Street')
      .contains('Anytown, USA 12345')
      .contains('555-555-5555')
  });
});
