
describe('Testes de pesquisas', () => {
    beforeEach(() => {
        cy.acessarHome()
        cy.wait(5000)
    })

    it('Realizar uma pesquisa válida e verificar o resultado', () => {
        const termoPesquisa = 'produtos';
        cy.realizarPesquisa(termoPesquisa);
        cy.url().should('include', `s=${encodeURIComponent(termoPesquisa)}`);
        cy.get('#search-field').should('have.value', termoPesquisa);
        cy.get('h1').should('contain.text', termoPesquisa);
    });

    it('Tentar pesquisar com termo inválido e verificar mensagem de erro', () => {
        const termoPesquisa = 'produto_inexistente'
        cy.realizarPesquisa(termoPesquisa);
        cy.url().should('include', `s=${encodeURIComponent(termoPesquisa)}`)
        cy.get('#search-field').should('have.value', termoPesquisa)
        cy.get('.no-results').should('be.visible')
        cy.get('h1').should('contain.text', termoPesquisa)
        cy.get('p')
            .should('contain.text', 'Lamentamos, mas nada foi encontrado para sua pesquisa, tente novamente com outras palavras.')
    })

    it('Realizar uma pesquisa sem inserir termo e verificar comportamento', () => {
        const termoPesquisa = ""
        cy.realizarPesquisa(termoPesquisa);
        cy.url().should('include', `s=${encodeURIComponent(termoPesquisa)}`)
        cy.get('#search-field').should('have.value', termoPesquisa)
        cy.get('h1').should('contain.text', termoPesquisa)
        cy.get('article').should('have.length.greaterThan', 0);
    })
})