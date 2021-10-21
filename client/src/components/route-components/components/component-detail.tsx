import { FormComponent } from '../../../types';
import { useState } from 'react';
import { LiveProvider, LiveEditor } from 'react-live';
import dracula from 'prism-react-renderer/themes/dracula';
import { Link as RRLink } from 'react-router-dom';
import { Input, Textarea, Button, Link, FormLabel, FormControl, Box } from '@chakra-ui/react';

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
        <div className="m-top-2">
          <FormControl isRequired>
            <FormLabel>Component key</FormLabel>
            <Input
              variant="filled"
              onChange={(e) => setWorkingComponent({ ...workingComponent, key: e.target.value })}
              value={workingComponent.key || ''}
            />
          </FormControl>
        </div>
        <div className="m-top-2">
          <FormControl>
            <FormLabel>Description</FormLabel>
            <Textarea
              variant="filled"
              onChange={(e) => setWorkingComponent({ ...workingComponent, description: e.target.value })}
              value={workingComponent.description || ''}
            />
          </FormControl>
        </div>
        <div className="editor-input-med m-top-2">
          <FormControl isRequired>
            <FormLabel>Code</FormLabel>
            <Box borderRadius="md" overflow="hidden">
              <LiveProvider code={workingComponent.code || ''} theme={dracula}>
                <LiveEditor onChange={(code) => setWorkingComponent({ ...workingComponent, code })} language="tsx" />
              </LiveProvider>
            </Box>
          </FormControl>
        </div>
      </div>
      <div className="button-list m-top-2">
        <Link as={RRLink} to="/components">
          Cancel
        </Link>
        <Button colorScheme="blue" onClick={() => save(workingComponent)}>
          Save
        </Button>
      </div>
    </>
  );
}
