import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import './App.css';
import ComponentsListRoute from './routes/components';
import ComponentDetailRoute from './routes/components/[key]';
import NewComponentDetailRoute from './routes/components/new';
import Page from './components/layout/page';
import NotImplemented from './routes/notimplemented';

function App() {
  return (
    <BrowserRouter>
      <Page>
        <Switch>
          <Route path="/components" exact>
            <ComponentsListRoute />
          </Route>
          <Route path="/component/new" exact>
            <NewComponentDetailRoute />
          </Route>
          <Route path="/component/:key" exact>
            <ComponentDetailRoute />
          </Route>
          <Route path="/notimplemented" exact>
            <NotImplemented />
          </Route>
        </Switch>
      </Page>
    </BrowserRouter>
  );
}

export default App;
