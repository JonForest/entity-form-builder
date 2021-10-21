import { Link } from 'react-router-dom';

export default function SideMenu() {
  return (
    <div id="side-menu">
      <ul className="level1">
        <li>
          Configure
          <ul className="level2">
            <li>
              <Link to="/entity-definition">Entity definition</Link>
            </li>
            <li>
              <Link to="/form-definition">Form definition</Link>
            </li>
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
              <Link to="/preview/dataclass">Data classes</Link>
            </li>
            <li>
              <Link to="/preview/ddl">Database table DDL</Link>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  );
}
