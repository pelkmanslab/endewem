function tests = phi1Test()
    tests = functiontests(localfunctions);
end


function drag = buildExampleDrag()
import endewem.*;

drag = newDrag();
drag.V = {'r', 'v1', 'v2', 'v3', 'v4'};
drag.r = 1;
drag.E = [[1 2]; [1 5]]; % the rest connect with the function.
appendArc(drag, 'v1', 'v2');
appendArc(drag, 'v1', 'v4');
drag.G = {'g1', 'g2', 'g3', 'g4'};
drag = map(drag, 'v1', 'g2');

end

function testExistingGeneInClass(testCase)

import endewem.*;

drag = buildExampleDrag();
drag = map(drag, 'v2', 'g3');

expectedWeight = uint8(1); % == g3 \in v2
actualWeight = Phi1(drag, 'g3', 'v2');
testCase.verifyEqual(actualWeight, expectedWeight, ...
    'Actual weight is different from expected.');
  
end

function testNonExistingGeneInClass(testCase)

import endewem.*;

drag = buildExampleDrag();
drag = map(drag, 'v2', 'g3');

expectedWeight = uint8(0); % == not g4 \in v2
actualWeight = Phi1(drag, 'g4', 'v2');
testCase.verifyEqual(actualWeight, expectedWeight, ...
    'Actual weight is different from expected.');

end

