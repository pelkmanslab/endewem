function weight = Phi3(drag, g, v)
%PHI3 a discretized measure of differential expression, picked from drag.X
%
% where
% * drag is an ontology DRAG struct. See newDrag() for details.
% * g - is a gene of interest
% * v - is a vertice or a classification from drag
%
%Function returns weight \in \Z^+ (uint8).

import endewem.*

geneIndex = indexOfGene(drag, g);
verticeIndex = indexOfVertice(drag, v);

weight = uint8(0);
% If g \in v, return X(g), otherwise 0.
annotationIndexes = find(ismember(drag.A(:,1), verticeIndex));
indexes = find(ismember(drag.A(annotationIndexes,2), geneIndex));
if ~isempty(indexes)
    weight = uint8(drag.X(geneIndex));
end

end
