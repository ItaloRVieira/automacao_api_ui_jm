export class HomePage {
  containerBusca = '.ast-search-menu-icon';
  botaoBusca = 'a.astra-search-icon[aria-label="Search button"]';
  inputBusca = '.search-field';

  acessarHome() {
    cy.visit('/#');
  }

  abrirBusca() {
    cy.get(this.botaoBusca, { timeout: 15000 })
      .filter(':visible')
      .first()
      .should('exist')
      .scrollIntoView()
      .then(($el) => {
        $el[0].click();
      });

    cy.get(this.containerBusca, { timeout: 15000 }).should(($container) => {
      const html = $container.html();
      expect(html).to.not.be.undefined;
    });

    cy.get(this.inputBusca, { timeout: 15000 })
      .should('exist');
  }

  digitarBusca(texto) {
    cy.get(this.inputBusca)
      .should('be.visible')
      .type(`${texto}{enter}`);
  }
}

