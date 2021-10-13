import { PropsWithChildren } from 'react';
import { Link } from 'react-router-dom';

enum ButtonType {
  primary = 'primary',
}

interface ButtonCommonInterface {
  buttonType?: ButtonType;
  children: any;
  to?: string;
  action?: () => void;
}

// interface ButtonToInterface extends ButtonCommonInterface {
//   to: string;
//   // action: never;
// }
//
// interface ButtonActionInterface extends ButtonCommonInterface {
//   action: () => void;
//   // to: never;
// }

// type ButtonInterface = ButtonToInterface | ButtonActionInterface;

export default function Button({
  to,
  action,
  buttonType = ButtonType.primary,
  children,
}: PropsWithChildren<ButtonCommonInterface>) {
  if (to) {
    return (
      <Link to={to} className="buttonLink">
        <button className={buttonType}>{children}</button>
      </Link>
    );
  }

  return (
    <button type="button" className={buttonType} onClick={action}>
      {children}
    </button>
  );
}
