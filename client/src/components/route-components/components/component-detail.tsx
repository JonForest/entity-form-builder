import { FormComponent } from '../../../types';
import { useState } from 'react';
import { LiveProvider, LiveEditor } from 'react-live';
import dracula from 'prism-react-renderer/themes/dracula';
import { Link } from 'react-router-dom';
import Button from '../../common/button';

export default function ComponentDetail({
  component,
  save,
}: {
  component: Partial<FormComponent>;
  save: (component: Partial<FormComponent>) => void;
}) {
  const [workingComponent, setWorkingComponent] = useState({ ...component });
  return (
    <>
      <div>
        <div>
          <input
            className="editor-input-big"
            onChange={(e) => setWorkingComponent({ ...workingComponent, key: e.target.value })}
            value={workingComponent.key || ''}
          />
        </div>
        <div>
          <textarea
            className="editor-input-med"
            onChange={(e) => setWorkingComponent({ ...workingComponent, description: e.target.value })}
            value={component.description || ''}
          />
        </div>
        <div className="editor-input-med">
          <LiveProvider code={workingComponent.code || ''} theme={dracula}>
            <LiveEditor onChange={(code) => setWorkingComponent({ ...workingComponent, code })} language="tsx" />
          </LiveProvider>
        </div>
      </div>
      <div className="button-list m-top-2">
        <Link to="/">Cancel</Link>
        <Button onClick={() => save(workingComponent)}>Save</Button>
      </div>
    </>
  );
}
