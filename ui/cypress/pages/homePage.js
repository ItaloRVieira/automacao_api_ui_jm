export class HomePage {
  lupaBusca = '.ast-icon > .ahfb-svg-iconset';
  inputBusca = '.search-field';

  acessarHome() {
    cy.visit('/#');
  }

  abrirBusca() {
    cy.get(this.lupaBusca).should('be.visible').click({ force: true });
  }

  digitarBusca(texto) {
    cy.get(this.inputBusca)
      .should('be.visible')
      .type(`${texto}{enter}`);
  }
}

