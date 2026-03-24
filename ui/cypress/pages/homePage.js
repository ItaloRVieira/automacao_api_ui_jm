export class HomePage {
  lupaBusca = '[aria-label="Search button"]';
  inputBusca = '.search-field';

  acessarHome() {
    cy.visit('/#');
  }

  abrirBusca() {
    cy.get(this.lupaBusca).first().should('be.visible').click({ force: true });
  }

  digitarBusca(texto) {
    cy.get(this.inputBusca)
      .should('be.visible')
      .type(`${texto}{enter}`);
  }
}

