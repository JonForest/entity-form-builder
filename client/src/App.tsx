import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import './App.css';
import ComponentsListRoute from './routes/components';
import ComponentDetailRoute from './routes/components/[key]';
import NewComponentDetailRoute from './routes/components/new';

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact>
          <ComponentsListRoute />
        </Route>
        <Route path="/component/new" exact>
          <NewComponentDetailRoute />
        </Route>
        <Route path="/component/:key" exact>
          <ComponentDetailRoute />
        </Route>
      </Switch>
    </BrowserRouter>
  );
}

export default App;
