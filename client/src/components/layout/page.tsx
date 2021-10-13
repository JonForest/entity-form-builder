import { PropsWithChildren } from 'react';
import SideMenu from './side-menu';

export default function Page({ children }: PropsWithChildren<{}>) {
  return (
    <div id="page">
      <div>
        <SideMenu />
      </div>
      <div className="child-container">{children}</div>
    </div>
  );
}
