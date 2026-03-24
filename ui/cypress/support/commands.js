import { HomePage } from '../pages/HomePage';

const home = new HomePage();        

Cypress.Commands.add('acessarHome', () => {
    home.acessarHome();
});

Cypress.Commands.add('realizarPesquisa', (termo) => {
    home.abrirBusca();
    home.digitarBusca(termo);
});

