import { ComponentType } from '../../../types';
import { useState } from 'react';
import { LiveProvider, LiveEditor } from 'react-live';
import dracula from 'prism-react-renderer/themes/dracula';
import { Link } from 'react-router-dom';

export default function ComponentDetail({
  component,
  save,
}: {
  component: Partial<ComponentType>;
  save: (component: Partial<ComponentType>) => void;
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
          >
            {component.description || ''}
          </textarea>
        </div>
        <div className="editor-input-med">
          <LiveProvider code={workingComponent.code || ''} theme={dracula}>
            <LiveEditor onChange={(code) => setWorkingComponent({ ...workingComponent, code })} language="tsx" />
          </LiveProvider>
        </div>
      </div>
      <Link to="/">Cancel</Link>
      <button type="button" onClick={() => save(workingComponent)}>
        Save
      </button>
    </>
  );
}
