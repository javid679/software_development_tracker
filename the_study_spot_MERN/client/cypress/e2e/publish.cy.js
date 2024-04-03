describe('Publish component', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000/Publish') // replace with the actual URL of your app
  })

  it('should allow user to publish an article', () => {
    // fill in the form
    cy.get('#title').type('My article title')
    cy.get('#technology').check()
    cy.get('#description').type('This is my article description.')
    cy.get('#article').type('This is the article content.')

    // submit the form
    cy.get('form').submit()

    // assert that the article is published
  })

  it('should not allow user to publish an article without a title', () => {
    // fill in the form without a title
    cy.get('#technology').check()
    cy.get('#description').type('This is my article description.')
    cy.get('#article').type('This is the article content.')

    // submit the form
    cy.get('form').submit()

    // assert that there is an error message
  })
})
