import { useApi } from '../hooks/use-api';
import ComponentsList from '../components/route-components/components/component-list';
import { FormComponent } from '../types';

import { Button, Heading } from '@chakra-ui/react';
import { Link } from 'react-router-dom';

export default function ComponentsListRoute() {
  const { isLoading, error, data } = useApi<Array<FormComponent>>('http://localhost:8080/api/component');

  console.log(isLoading, error, data);
  if (isLoading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;
  if (!data) return <p>Error: No data</p>;

  return (
    <>
      <Heading>Component list</Heading>
      <ComponentsList components={data} />
      <div style={{ paddingTop: '11px' }}>
        <Button as={Link} to="/component/new">
          Add component
        </Button>
      </div>
    </>
  );
}
