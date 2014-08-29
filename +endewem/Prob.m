function pValue = Prob(drag, phi_a, phi_b, H, v)
%PROB Compute p-value shorthand for Phi_A|Phi_B
%   Naive implementation.

import endewem.*;

pValue = 0;
A_max = PhiH(drag, phi_a, H, v);
B_max = PhiH(drag, phi_b, H, v);

end

