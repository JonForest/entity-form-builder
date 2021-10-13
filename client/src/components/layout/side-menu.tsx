import { PropsWithChildren } from 'react';
import { Link } from 'react-router-dom';

export default function SideMenu({}: PropsWithChildren<{}>) {
  return (
    <div id="side-menu">
      <ul className="level1">
        <li>
          Configure
          <ul className="level2">
            <li>
              <Link to="/components">Field components</Link>
            </li>
          </ul>
        </li>
        <li>
          Preview
          <ul className="level2">
            <li>
              <Link to="/notimplemented">Forms</Link>
            </li>
            <li>
              <Link to="/notimplemented">Routing</Link>
            </li>
            <li>
              <Link to="/notimplemented">Data classes</Link>
            </li>
            <li>
              <Link to="/notimplemented">Database table DDL</Link>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  );
}
