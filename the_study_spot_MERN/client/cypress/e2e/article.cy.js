describe('Article component', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000/Article');
  });

  it('renders article data', () => {
    const article = {
      title: 'Article Title',
      name: 'Author Name',
      description: 'Article Description',
      article: 'Article Content',
    };
    localStorage.setItem('articleId', '123');

  });

  it('handles error when retrieving article', () => {
    localStorage.setItem('articleId', '123');

    cy.intercept('GET', '**/api/article/articleid/*', { statusCode: 500 }).as('getArticleWithError');
    cy.wait('@getArticleWithError');
  });
});
