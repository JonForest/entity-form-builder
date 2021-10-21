import { Link } from 'react-router-dom';
import { FormComponent } from '../../../types';
import { Box } from '@chakra-ui/react';

interface ComponentListItemInterface {
  component: FormComponent;
}
export default function ComponentListItem({ component }: ComponentListItemInterface) {
  return (
    <li>
      <Box borderWidth="1px" borderRadius="lg">
        <Link to={`/component/${component.key}`}>
          <Box borderBottomWidth="1px" width="full" px="2" pt="1">
            <div className="key">{component.key}</div>
          </Box>
          <Box px="2" py="2">
            <div className="description">{component.description}</div>
          </Box>
        </Link>
      </Box>
    </li>
  );
}
