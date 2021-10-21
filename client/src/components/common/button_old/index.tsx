import { PropsWithChildren } from 'react';
import { Link } from 'react-router-dom';

enum ButtonType {
  primary = 'primary',
}

interface ButtonCommonInterface {
  buttonType?: ButtonType;
  disabled?: boolean;
}

interface ButtonToInterface extends ButtonCommonInterface {
  to: string;
  onClick?: never;
}

interface ButtonActionInterface extends ButtonCommonInterface {
  onClick: () => void;
  to?: never;
}

type ButtonInterface = ButtonToInterface | ButtonActionInterface;

export default function Button({
  to,
  onClick,
  disabled,
  buttonType = ButtonType.primary,
  children,
}: PropsWithChildren<ButtonInterface>) {
  if (to) {
    return (
      <Link to={to} className="buttonLink">
        <button className={buttonType}>{children}</button>
      </Link>
    );
  }

  return (
    <button type="button" className={buttonType} onClick={onClick} disabled={disabled}>
      {children}
    </button>
  );
}
