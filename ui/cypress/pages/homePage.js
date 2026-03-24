export class HomePage {
  lupaBusca = 'a.astra-search-icon[aria-label="Search button"]';
  inputBusca = '.search-field';

  acessarHome() {
    cy.visit('/#');
  }

  abrirBusca() {
    cy.get(this.lupaBusca, { timeout: 10000 })
      .should('exist')
      .should('be.visible')
      .scrollIntoView()
      .click();

    cy.get(this.inputBusca, { timeout: 10000 })
      .should('be.visible');
  }

  digitarBusca(texto) {
    cy.get(this.inputBusca)
      .should('be.visible')
      .type(`${texto}{enter}`);
  }
}

