import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import 'App.css';
import ComponentsListRoute from './routes/components';
import ComponentDetailRoute from './routes/components/[key]';
import NewComponentDetailRoute from './routes/components/new';
import Page from './components/layout/page';
import NotImplemented from './routes/notimplemented';
import FormDefinition from './routes/form-definition';
import EntityDefinition from './routes/entity-definition';
import PreviewDDL from './routes/preview/preview-ddl';
import PreviewDataclass from './routes/preview/preview-dataclass';

function App() {
  return (
    <BrowserRouter>
      <Page>
        <Switch>
          <Route path="/form-definition" exact>
            <FormDefinition />
          </Route>
          <Route path="/entity-definition" exact>
            <EntityDefinition />
          </Route>
          <Route path="/components" exact>
            <ComponentsListRoute />
          </Route>
          <Route path="/component/new" exact>
            <NewComponentDetailRoute />
          </Route>
          <Route path="/component/:key" exact>
            <ComponentDetailRoute />
          </Route>
          <Route path="/preview/ddl" exact>
            <PreviewDDL />
          </Route>
          <Route path="/preview/dataclass" exact>
            <PreviewDataclass />
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
