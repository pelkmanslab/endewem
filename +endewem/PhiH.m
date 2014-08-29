function weight = PhiH(drag, phi, H, v)
%PHIH is an implementation of Phi(H, v)
% Where 
%  drag - is an ontology DRAG struct. See newDrag() for details.
%  phi  - is a handle 
%  H    - is subset of genes of interest
%  v    - is an annotation class
% weight is a sum over Phi(g, v) for each g \in H, H \subset G

weight = uint16(0);
for index = 1:numel(H)
    weight = weight + uint16(phi(drag, H(index), v));
end

end
